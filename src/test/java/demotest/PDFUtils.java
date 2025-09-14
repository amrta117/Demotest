package demotest;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtils {

	// Compare two PDFs by text content using only file names
	public static boolean comparePDFs(String fileName1, String fileName2) throws IOException {
		// Get project root path
		String projectPath = System.getProperty("user.dir");

		// Build file paths inside Downloads folder
		File file1 = new File(projectPath + File.separator + "Downloads" + File.separator + fileName1);
		File file2 = new File(projectPath + File.separator + "Downloads" + File.separator + fileName2);

		if (!file1.exists() || !file2.exists()) {
			throw new IOException("One or both files do not exist in the Downloads folder!");
		}

		// Load both PDFs and compare their text
		try (PDDocument doc1 = PDDocument.load(file1); PDDocument doc2 = PDDocument.load(file2)) {

			PDFTextStripper stripper = new PDFTextStripper();
			String text1 = stripper.getText(doc1).replaceAll("\\s+", " ").trim();
			String text2 = stripper.getText(doc2).replaceAll("\\s+", " ").trim();

			return text1.equals(text2);
		}
	}

}