import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task7_TestDataGenerater {
	
	private static final Random random = new Random();
	
	public static void main(String[] args) {
		int Max_NumberOfBooks = 1000000; // ドキュメントの数
		int Max_NumberOfWords = 10000; // ドキュメン内の文字数
		int Max_NumberOfAllWords = 1000000; //ドキュメント全体の文字数
		int Max_NumberOfUrls = 10;  // ドキュメント内のURL数
		
		
		
		File sourceDir = new File("source");
		if(!sourceDir.exists()) {
			sourceDir.mkdir();
		}
		
		int totalSize = 0;
        int documentCount = 0;
        
        try {
            for (int i = 0; i < Max_NumberOfBooks ;i++) {
                List<String> keywords = generateRandomKeywords(random.nextInt(Max_NumberOfWords) + 1);
                List<String> urls = generateRandomUrls(random.nextInt(Max_NumberOfUrls + 1));

                String content = String.join(" ", keywords) + "\n" + String.join("\n", urls);
                int docSize = content.length();

                if (totalSize + docSize > Max_NumberOfAllWords) {
                    break;
                }

                totalSize += docSize;

                String fileName = generateRandomString(10) + ".html";
                FileWriter writer = new FileWriter(new File(sourceDir, fileName));
                writer.write(content);
                writer.close();

                documentCount++;
                
                System.out.println("fileName: " + fileName);
                for (String url : urls) {
                    System.out.println(url);
                }
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println(documentCount + " documents generated with total size " + totalSize + " bytes.");
	}
	
	
	private static List<String> generateRandomKeywords(int count) {
        List<String> keywords = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            keywords.add(generateRandomString(random.nextInt(8) + 3));
        }
        return keywords;
    }
	
	private static List<String> generateRandomUrls(int count) {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            urls.add(generateRandomString(random.nextInt(8) + 3) + ".html");
        }
        return urls;
    }

	private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
	}
}
