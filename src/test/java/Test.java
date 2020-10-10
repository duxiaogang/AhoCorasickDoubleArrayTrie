import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;
import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie.Hit;

public class Test {
	private AhoCorasickDoubleArrayTrie<String> screenWord = new AhoCorasickDoubleArrayTrie<>();

	public void initScreenWord() {
		TreeMap<String, String> map = new TreeMap<>();
		String[] keyArray = new String[] {
			"sb",
			"2b",
			"𐏐",
		};
		for (String key : keyArray) {
			map.put(key, key);
		}
		screenWord.build(map);
	}

	public boolean initScreenWordFromFile(String file) {
		try
		{
			Scanner in = new Scanner(Path.of(file), StandardCharsets.UTF_8);
			TreeMap<String, String> map = new TreeMap<>();
			while (in.hasNextLine()) {
				String key = in.nextLine().strip();
				map.put(key, key);
			}
			screenWord.build(map);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	public String screenText(String text) {
		StringBuilder stringBuilder = new StringBuilder(text);
		screenWord.parseText(text, (begin, end, value) -> {
			//System.out.printf("[%d:%d]=%s\n", begin, end, value);
			for (int i=begin; i<end; i++) {
				stringBuilder.setCharAt(i, '*');
			}
		});
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		//Test test = new Test();
		//test.initScreenWord();
		////String newText = test.screenText("hah, 2sb");
		////String newText = test.screenText("haha, 你这个2b, 哈哈哈");
		////String newText = test.screenText("haha, 你这个22bb, 哈哈哈");
		////String newText = test.screenText("haha, 𐏐, 你这个2b, 哈哈哈");
		//String newText = test.screenText("haha, 𐏐, 你这个2bdsb, 哈哈哈");
		//System.out.println(newText);

		Test test = new Test();
		if (!test.initScreenWordFromFile("dictionary.txt")) {
			return;
		}

		String text = null;
		try
		{
			StringBuilder stringBuilder = new StringBuilder();
			Scanner in = new Scanner(Path.of("text.txt"), StandardCharsets.UTF_8);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
			text = stringBuilder.toString();
		}
		catch(Exception e)
		{
		}

		for (int i=0; i<1; i++) {
			long bt = System.currentTimeMillis();
			String newText = test.screenText(text);
			long et = System.currentTimeMillis();
			System.out.println(et-bt);
			System.out.println(newText);
		}
	}
};
