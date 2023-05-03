package com.apryse.usecase;

import com.pdftron.common.PDFNetException;
import com.pdftron.pdf.PDFDoc;
import com.pdftron.pdf.PDFNet;
import com.pdftron.sdf.SDFDoc;
import com.pdftron.sdf.SecurityHandler;

public class PasswordEncryptedPDF {

	public static void main(String args[]) {
		System.out.println("Password Encrypting file");
		String filename = "C:\\Users\\priyam.saha\\Downloads\\Mockito for Spring.pdf";
		String outputPath = "C:\\Users\\priyam.saha\\Downloads\\";
		encryptPDFWithPassword(filename, outputPath);
	}

	public static void encryptPDFWithPassword(String filename, String outputPath) {
		String license = "demo:1683010200630:7ddf8025030000000056367cdc990a76a9d4d4d7bbdad93dbd3b1295f7";
		
		PDFNet.initialize(license);
		PDFDoc doc;
		try {
			doc = new PDFDoc(filename);
			// Apply a new security handler with given security settings.
			// In order to open saved PDF you will need a user password 'test'.
			SecurityHandler new_handler = new SecurityHandler();

			// Set a new password required to open a document
			String user_password = "priyam_saha";
			new_handler.changeUserPassword(user_password);

			// Set Permissions
			new_handler.setPermission(SecurityHandler.e_print, true);
			new_handler.setPermission(SecurityHandler.e_extract_content, false);

			// Note: document takes the ownership of new_handler.
			doc.setSecurityHandler(new_handler);
			doc.save((outputPath + "secured.pdf"), SDFDoc.SaveMode.NO_FLAGS, null);
		
			doc.close();

			PDFNet.terminate();
		} catch (PDFNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
