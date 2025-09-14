package utils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {

	// waits for file download and renames it
	public static File waitAndRenameDownloadedFile(String fileName, String newName) throws InterruptedException {
		String projectDir = System.getProperty("user.dir");
		String downloadDir = projectDir + File.separator + "Downloads";
		File downloadedFile = new File(downloadDir, fileName);

		// wait until file is downloaded
		int counter = 0;
		while (!downloadedFile.exists() && counter < 15) {
			Thread.sleep(1000); // wait 1s
			counter++;
		}

		File renamedFile = new File(downloadDir, newName);
		if (downloadedFile.renameTo(renamedFile)) {
			System.out.println("File renamed to: " + newName);
		} else {
			System.out.println("Failed to rename file!");
		}
		return renamedFile;
	}

	public static void clearFilesInDir(String dir) throws IOException {
		Path dir2 = Paths.get(dir);

		try (Stream<Path> s = Files.walk(dir2)) {
			s.filter(Files::isRegularFile).forEach(p -> {
				try {
					Files.delete(p);
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			});
		}
	}
}
