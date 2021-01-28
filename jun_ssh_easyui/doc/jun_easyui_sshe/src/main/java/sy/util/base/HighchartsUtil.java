package sy.util.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;

/**
 * 用于下载highchart图表
 * 
 * @author Wujun
 * 
 */
public class HighchartsUtil extends HttpServlet {

	public HighchartsUtil() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");
		String svg = request.getParameter("svg");
		String filename = request.getParameter("filename");
		filename = filename == null ? "chart" : filename;

		if (null == type || null == svg) {// 说明浏览器支持大数据上传，所以这地方需要从大数据流里面取数据
			BufferedReader rader = request.getReader();
			String str;
			int i = 0;
			while ((str = rader.readLine()) != null) {
				i++;
				if (i == 4) {
					filename = str;
					continue;
				}
				if (i == 8) {
					type = str;
					continue;
				}
				if (i == 20) {
					svg = str;
					break;
				}
			}
		}

		// System.out.println(filename);
		// System.out.println(type);
		// System.out.println(svg);

		ServletOutputStream out = response.getOutputStream();
		if (null != type && null != svg) {
			svg = svg.replaceAll(":rect", "rect");
			String ext = "";
			Transcoder t = null;
			if (type.equals("image/png")) {
				ext = "png";
				t = new PNGTranscoder();
			} else if (type.equals("image/jpeg")) {
				ext = "jpg";
				t = new JPEGTranscoder();
				t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
			} else if (type.equals("application/pdf")) {
				ext = "pdf";
				t = (Transcoder) new PDFTranscoder();
			} else if (type.equals("image/svg+xml"))
				ext = "svg";
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename + "." + ext, "UTF-8"));
			response.addHeader("Content-Type", type);
			if (null != t) {
				TranscoderInput input = new TranscoderInput(new StringReader(svg));
				TranscoderOutput output = new TranscoderOutput(out);
				try {
					t.transcode(input, output);
				} catch (TranscoderException e) {
					out.print("Problem transcoding stream. See the web logs for more details.");
					e.printStackTrace();
				}
			} else if (ext.equals("svg")) {
				OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
				writer.append(svg);
				writer.close();
			} else
				out.print("Invalid type: " + type);
		} else {
			response.addHeader("Content-Type", "text/html");
			out.println("Usage:\n\tParameter [svg]: The DOM Element to be converted." + "\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
		}
		out.flush();
		out.close();
	}

}
