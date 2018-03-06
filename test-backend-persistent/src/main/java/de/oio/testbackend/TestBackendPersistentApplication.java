package de.oio.testbackend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class TestBackendPersistentApplication {

	private List<String> entries = new ArrayList<>();
	private static final String STORAGE_FILENAME = "/vol/storage.txt";

	public static void main(String[] args) {
		SpringApplication.run(TestBackendPersistentApplication.class, args);
	}

	@RequestMapping(value = "/createEntry", method = RequestMethod.GET)
	@ResponseBody
	public String createEntry() {
		String entry = UUID.randomUUID().toString();
		entries.add(entry);
		return entry;
	}

	@RequestMapping(value = "/entries", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String getEntries() {
		String result = "Entries (" + entries.size() + "): \n\n";
		for (String entry : entries) {
			result += entry + "\n";
		}

		return result;
	}

	@RequestMapping(value = "/load", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String load() {
		try {
			entries = Files.readAllLines(Paths.get(STORAGE_FILENAME));
		} catch (IOException e) {
			return "Error: " + e.getMessage();
		}
		return "read " + entries.size() + " from " + STORAGE_FILENAME;
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String clear() {
		entries.clear();
		return "cleared " + entries.size() + " entries in memory";
	}

	@RequestMapping(value = "/persist", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String persist() {
		try (PrintWriter out = new PrintWriter(STORAGE_FILENAME)) {
			for (String entry : entries) {
				out.println(entry);
			}
		} catch (FileNotFoundException e) {
			return "Error: " + e.getMessage();
		}
		return "Persisted " + entries.size() + " entries to" + STORAGE_FILENAME;
	}
}
