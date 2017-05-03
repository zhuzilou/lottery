package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@FunctionalInterface
interface Converter<F, T> {
	F convert(T from);
}

public class Something {
	String startsWith(String s) {
		return String.valueOf(s.charAt(0));
	}

	String test(String t) {
		return t;
	}

	int convertInt(String s) {
		return Integer.valueOf(s);
	}

	public static void main(String[] args) {
		Something something = new Something();
		Converter<String, String> converter = (s) -> something.startsWith(s);
		System.out.println(converter.convert("Java"));

		converter = something::test;
		System.out.println(converter.convert("abc"));

		Converter<Integer, String> tToF = something::convertInt;
		System.out.println(tToF.convert("123"));

		Collection<Collection<Collection<String>>> test = new ArrayList<>();
		Collection<String> a = new ArrayList<>();
		a.add("a1");
		a.add("a2");

		Collection<String> b = new ArrayList<>();
		b.add("b1");
		b.add("b2");

		Collection<Collection<String>> a1 = new ArrayList<>();
		a1.add(a);
		a1.add(b);

		Collection<String> d = new ArrayList<>();
		d.add("d1");
		d.add("d2");
		Collection<String> e = new ArrayList<>();
		e.add("e1");
		e.add("e2");

		Collection<Collection<String>> b1 = new ArrayList<>();
		b1.add(d);
		b1.add(e);

		test.add(a1);
		test.add(b1);

		test.stream().flatMap(all -> all.stream()).flatMap(single -> single.stream()).collect(Collectors.toList())
				.forEach(s -> {
					System.out.println(s);
				});

		a1.stream().flatMap(t -> t.stream()).collect(Collectors.toList());
		test.stream().flatMap(all -> all.stream()).flatMap(single -> single.stream()).map(r -> r.length())
				.forEach(i -> {
					System.out.println(i);
				});

		Predicate<String> predicate = s -> s.length() > 0;
		System.out.println(predicate.test("foo"));
		System.out.println(predicate.negate().test("foo"));

		Predicate<Boolean> nonNull = Objects::nonNull;
		System.out.println(nonNull.test(null));

		Predicate<String> isEmpty = Objects::isNull;
		System.out.println(isEmpty.test(""));

		Optional<Collection<String>> emptyCol = Optional.of(new ArrayList<>());
		System.out.println(emptyCol.isPresent());
		Predicate<Collection<String>> colIsEmpty = c -> c.isEmpty();
		System.out.println(colIsEmpty.test(new ArrayList<>()));

		Collection<String> sortCol = new ArrayList<>();
		sortCol.add("r");
		sortCol.add("a");

		sortCol.stream().sorted().forEach(System.out::println);
		System.out.println(sortCol);
	}
}
