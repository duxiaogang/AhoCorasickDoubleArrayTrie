import java.io.*;
import java.util.*;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;
import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie.Hit;

public class Test {
	private AhoCorasickDoubleArrayTrie<String> screenWord = new AhoCorasickDoubleArrayTrie<>();

	public boolean initScreenWord() {
		TreeMap<String, String> map = new TreeMap<>();
		String[] keyArray = new String[] {
			"sb",
			"2b",
		};
		for (String key : keyArray) {
			map.put(key, key);
		}
		screenWord.build(map);
		return true;
	}

	public String screenText(String text) {
		StringBuilder stringBuilder = new StringBuilder(text);
		screenWord.parseText(text, (begin, end, value) -> {
			System.out.printf("[%d:%d]=%s\n", begin, end, value);
			for (int i=begin; i<end; i++) {
				stringBuilder.setCharAt(i, '*');
			}
		});
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.initScreenWord();
		//String newText = test.screenText("hah, 2sb");
		//String newText = test.screenText("haha, 你这个2b, 哈哈哈");
		//String newText = test.screenText("haha, 你这个22bb, 哈哈哈");
		//String newText = test.screenText("haha, 𐏐, 你这个2b, 哈哈哈");
		String newText = test.screenText("haha, 𐏐, 你这个2bdsb, 哈哈哈");
		System.out.println(newText);
	}
};
