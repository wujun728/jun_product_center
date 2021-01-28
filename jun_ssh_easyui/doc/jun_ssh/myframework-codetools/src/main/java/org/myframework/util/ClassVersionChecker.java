package org.myframework.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

//The first 4 bytes are a magic number, 0xCAFEBABe, to identify a valid class file then the next 2 bytes identify the class format version (major and minor).
//Possible major/minor value :
//create table m2_lib ( library1 varchar2(100)) ;
//select '"'||replace(library1,'/','/')||'",' from m2_lib   ;
//
//select * from    m2_lib  for update;
//major  minor Java platform version
//45       3           1.0
//45       3           1.1
//46       0           1.2
//47       0           1.3
//48       0           1.4
//49       0           1.5
//50       0           1.6

public class ClassVersionChecker {
	public static void main(String[] args) throws IOException {
		// for (int i = 0; i < args.length; i++)
		// checkClassVersion(args[i]);
		String[] jarsStrings = { "E:\\.m2\\repository\\org\\springframework\\spring-aop\\3.2.8.RELEASE\\spring-aop-3.2.8.RELEASE.jar" };
		for (int i = 0; i < jarsStrings.length; i++)
			scanJarFileJDK1_5_Version(jarsStrings[i]);
	}



	private static void checkClassVersion(String filename) throws IOException {
		DataInputStream in = new DataInputStream(new FileInputStream(filename));
		int magic = in.readInt();
		if (magic != 0xcafebabe) {
			System.out.println(filename + " is not a valid class!");
			;
		}
		int minor = in.readUnsignedShort();
		int major = in.readUnsignedShort();
		System.out.println(filename + ": " + major + " . " + minor);
		in.close();
	}

	private static void checkClassVersion(InputStream in1) throws IOException {
		DataInputStream in = new DataInputStream(in1);
		int magic = in.readInt();
		if (magic != 0xcafebabe) {
			System.out.println(in + " is not a valid class!");
			;
		}
		int minor = in.readUnsignedShort();
		int major = in.readUnsignedShort();

		System.out.println(": " + major + " . " + minor);
		in.close();
	}

	private static void checkClassJDK1_5_Version(String jar, InputStream in1)
			throws IOException {
		DataInputStream in = new DataInputStream(in1);
		int magic = in.readInt();
		if (magic != 0xcafebabe) {
			System.out.println(in + " is not a valid class!");
			;
		}
		int minor = in.readUnsignedShort();
		int major = in.readUnsignedShort();
		if (major > 49)
			System.out.println(jar + ": " + major + " . " + minor);
		in.close();
	}

	public static void scanJarFileJDK1_5_Version(String zipname) {
		try {
			ZipInputStream zin = new ZipInputStream(
					new FileInputStream(zipname));
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.getName().endsWith(".class")) {
					ZipFile file = new ZipFile(zipname);
					InputStream in = file.getInputStream(entry);
					checkClassJDK1_5_Version(zipname, in);
					break;
				}
			}
			zin.close();
		} catch (IOException e) {
		}
	}

	public static void scanZipFile(String zipname) {
		try {
			ZipInputStream zin = new ZipInputStream(
					new FileInputStream(zipname));
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				System.out.println(entry.getName());
				zin.closeEntry();
			}
			zin.close();
		} catch (IOException e) {
		}
	}

	public static void scanClassFile(String zipname) {
		try {
			ZipInputStream zin = new ZipInputStream(
					new FileInputStream(zipname));
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.getName().endsWith(".class")) {
					System.out.println(entry.getName());
					ZipFile file = new ZipFile(zipname);
					InputStream in = file.getInputStream(entry);
					System.out.println(in);
					checkClassVersion(in);
					break;
				}
			}
			zin.close();
		} catch (IOException e) {
		}
	}

	public static void loadZipFile(String zipname, String name) {
		try {
			ZipInputStream zin = new ZipInputStream(
					new FileInputStream(zipname));
			ZipEntry entry;
			System.out.println("");
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.getName().equals(name)) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(zin));
					String s;
					while ((s = in.readLine()) != null)
						System.out.println(s + "/n");
				}
				zin.closeEntry();
			}
			zin.close();
		} catch (IOException e) {
		}
	}
}
