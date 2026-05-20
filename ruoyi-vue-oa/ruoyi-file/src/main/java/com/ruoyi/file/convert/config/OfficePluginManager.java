package com.ruoyi.file.convert.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jodconverter.core.office.InstalledOfficeManagerHolder;
import org.jodconverter.core.office.OfficeException;
import org.jodconverter.core.office.OfficeUtils;
import org.jodconverter.core.util.OSUtils;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 创建文件转换器
 *
 * @author wocurr.com
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@PropertySource(value = "classpath:application-file.properties")
@ConfigurationProperties(prefix = "office")
public class OfficePluginManager {

    private static final String DEFAULT_OFFICE_HOME_VALUE = "default";
    private static final String EXECUTABLE_DEFAULT = "program/soffice.bin";
    private LocalOfficeManager officeManager;

    @Value("${office.home}")
    private String home;

    @Value("${office.plugin.server.ports}")
    private String serverPorts;

    @Value("${office.plugin.task.timeout}")
    private String timeOut;

    @Value("${office.plugin.task.taskexecutiontimeout}")
    private String taskExecutionTimeout;

    @Value("${office.plugin.task.maxtasksperprocess}")
    private int maxTasksPerProcess;

    /**
     * 启动Office组件进程
     */
    @PostConstruct
    public void startOfficeManager() throws OfficeException {
        File officeHome;
        if (home != null && !DEFAULT_OFFICE_HOME_VALUE.equals(home)) {
            officeHome = new File(home);
        } else {
            officeHome = getDefaultOfficeHome();
        }
        if (officeHome == null) {
            log.error("找不到office组件，请确认'office.home'配置是否有误");
            return;
        }
        boolean killOffice = killProcess();
        if (killOffice) {
            log.warn("检测到有正在运行的office进程，已自动结束该进程");
        }
        try {
            String[] portsString = serverPorts.split(",");
            int[] ports = Arrays.stream(portsString).mapToInt(Integer::parseInt).toArray();
            long timeout = DurationStyle.detectAndParse(timeOut).toMillis();
            long taskexecutiontimeout = DurationStyle.detectAndParse(taskExecutionTimeout).toMillis();
            officeManager = LocalOfficeManager.builder()
                    .officeHome(officeHome)
                    .portNumbers(ports)
                    .processTimeout(timeout)
                    .maxTasksPerProcess(maxTasksPerProcess)
                    .taskExecutionTimeout(taskexecutiontimeout)
                    .build();
            officeManager.start();
            InstalledOfficeManagerHolder.setInstance(officeManager);
        } catch (Exception e) {
            log.error("启动office组件失败，请检查office组件是否可用");
            throw e;
        }
    }

    private boolean killProcess() {
        boolean flag = false;
        InputStream os = null;
        try {
            if (OSUtils.IS_OS_WINDOWS) {
                Process p = Runtime.getRuntime().exec("cmd /c tasklist ");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                os = p.getInputStream();
                byte[] b = new byte[256];
                while (os.read(b) > 0) {
                    baos.write(b);
                }
                String s = baos.toString();
                if (s.contains("soffice.bin")) {
                    Runtime.getRuntime().exec("taskkill /im " + "soffice.bin" + " /f");
                    flag = true;
                }
            } else {
                Process p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "ps -ef | grep " + "soffice.bin" + " |grep -v grep | wc -l"});
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                os = p.getInputStream();
                byte[] b = new byte[256];
                while (os.read(b) > 0) {
                    baos.write(b);
                }
                String s = baos.toString();
                if (!s.startsWith("0")) {
                    String[] cmd = {"sh", "-c", "ps -ef | grep soffice.bin | grep -v grep | awk '{print \"kill -9 \"$2}' | sh"};
                    Runtime.getRuntime().exec(cmd);
                    flag = true;
                }
            }
        } catch (IOException e) {
            log.error("检测office进程异常", e);
        } finally {
            IOUtils.closeQuietly(os);
        }
        return flag;
    }

    @PreDestroy
    public void destroyOfficeManager() {
        if (null != officeManager && officeManager.isRunning()) {
            log.info("Shutting down office process");
            OfficeUtils.stopQuietly(officeManager);
        }
    }

    private static File getDefaultOfficeHome() {
        return findOfficeHome(EXECUTABLE_DEFAULT,
                "/opt/libreoffice7.0",
                "/opt/libreoffice7.1",
                "/opt/libreoffice7.2",
                "/opt/libreoffice7.3",
                "/opt/libreoffice7.4",
                "/opt/libreoffice7.5",
                "/opt/libreoffice7.6",
                "/opt/libreoffice24.2",
                "/opt/libreoffice24.8",
                "/usr/lib64/libreoffice",
                "/usr/lib/libreoffice",
                "/usr/local/lib64/libreoffice",
                "/usr/local/lib/libreoffice",
                "/opt/libreoffice",
                "/usr/lib64/openoffice",
                "/usr/lib64/openoffice.org3",
                "/usr/lib64/openoffice.org",
                "/usr/lib/openoffice",
                "/usr/lib/openoffice.org3",
                "/usr/lib/openoffice.org",
                "/opt/openoffice4",
                "/opt/openoffice.org3");
    }

    private static File findOfficeHome(final String executablePath, final String... homePaths) {
        return Stream.of(homePaths)
                .filter(homePath -> Files.isRegularFile(Paths.get(homePath, executablePath)))
                .findFirst()
                .map(File::new)
                .orElse(null);
    }

}
