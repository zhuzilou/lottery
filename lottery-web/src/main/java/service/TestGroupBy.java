package service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TestGroupBy {

	public static void main(String[] args) {
		Collection<Demo> test = new ArrayList<>();
		Date first = new Date();
		test.add(new Demo(first, "a"));
		test.add(new Demo(first, "b"));
		test.add(new Demo(first, "c"));

		Calendar cal = Calendar.getInstance();
		cal.set(2016, 10, 1);
		test.add(new Demo(cal.getTime(), "d"));
		test.add(new Demo(cal.getTime(), "e"));

		cal.set(2015, 9, 3);

		test.add(new Demo(cal.getTime(), "f"));
		test.add(new Demo(cal.getTime(), "g"));
		test.add(new Demo(cal.getTime(), "h"));

		test.stream().forEach(demo -> {
			System.out.println(MessageFormat.format("Demo Name: {0} Date: {1}", demo.getName(), demo.getIssue()));
		});

		Map<Date, List<Demo>> result = test.parallelStream().collect(Collectors.groupingBy(demo -> {
			return demo.getIssue();
		}));
		Iterator<Entry<Date, List<Demo>>> it = result.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Date, List<Demo>> entry = it.next();
			System.out.println("+++++++++++++++++++++++++++++++++");
			System.out.println(entry.getKey());
			entry.getValue().parallelStream().forEach(System.out::println);
			System.out.println("+++++++++++++++++++++++++++++++++");
		}
	}

}

class Demo {
	private Date issue;
	private String name;

	public Demo(Date issue, String name) {
		super();
		this.issue = issue;
		this.name = name;
	}

	public Date getIssue() {
		return issue;
	}

	public void setIssue(Date issue) {
		this.issue = issue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
