package de.oio.testbackend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.oio.testbackend.domain.Entry;
import de.oio.testbackend.domain.EntryRepository;

@Controller
@SpringBootApplication
public class TestBackendDBApplication {

	private List<String> entries = new ArrayList<>();
	
	private final EntryRepository entryRepository;

	public static void main(String[] args) {
		SpringApplication.run(TestBackendDBApplication.class, args);
	}
	
	 @Inject
	  public TestBackendDBApplication(EntryRepository entryRepository) {
	    this.entryRepository = entryRepository;
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
		entries.clear();
		Iterator<Entry> it = entryRepository.findAll().iterator();
		while(it.hasNext()){
			entries.add(it.next().getData());
		}
		return "read " + entries.size() + " from db";
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
		entryRepository.deleteAll();
			for (String entry : entries) {
				Entry entr = new Entry();
				entr.setData(entry);
				entryRepository.save(entr);
			}
		return "Persisted " + entries.size() + " entries to db";
	}
}
