package de.oio.testbackend;

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
public class TestBackendApplication {

	private final List<String> entries = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(TestBackendApplication.class, args);
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
}
