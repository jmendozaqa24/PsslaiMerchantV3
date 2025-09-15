package PsslaiMerchantV3.Resource;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GChatNotifier {

	private static final String WEBHOOK_URL = "https://chat.googleapis.com/v1/spaces/AAQA2axPH8A/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=weNp4DlVraMlZYaeZv6-tluQzWKwRE97QXc8p-sAClg";

	public static void sendExecutionReport(String start, String end, String duration, int passed, int failed,
			int skipped, String reportUrl) {
		try {
			URL url = new URL(WEBHOOK_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setDoOutput(true);

// Build JSON payload for GChat card
			String jsonPayload = String.format(
	                "{\n" +
	                "  \"cards\": [\n" +
	                "    {\n" +
	                "      \"header\": {\n" +
	                "        \"title\": \"✅ Test Execution Report\",\n" +
	                "        \"subtitle\": \"Psslai Merchant Webtool Automation Suite Results\"\n" +
	                "      },\n" +
	                "      \"sections\": [\n" +
	                "        {\n" +
	                "          \"widgets\": [\n" +
	                "            { \"keyValue\": { \"topLabel\": \"Start\", \"content\": \"%s\" }},\n" +
	                "            { \"keyValue\": { \"topLabel\": \"End\", \"content\": \"%s\" }},\n" +
	                "            { \"keyValue\": { \"topLabel\": \"Duration\", \"content\": \"%s\" }}\n" +
	                "          ]\n" +
	                "        },\n" +
	                "        {\n" +
	                "          \"widgets\": [\n" +
	                "            { \"keyValue\": { \"topLabel\": \"Passed ✅\", \"content\": \"%d\" }},\n" +
	                "            { \"keyValue\": { \"topLabel\": \"Failed ❌\", \"content\": \"%d\" }},\n" +
	                "            { \"keyValue\": { \"topLabel\": \"Skipped ⚠️\", \"content\": \"%d\" }}\n" +
	                "          ]\n" +
	                "        },\n" +
	                "        {\n" +
	                "          \"widgets\": [\n" +
	                "            { \"buttons\": [\n" +
	                "                { \"textButton\": { \"text\": \"📊 OPEN REPORT\", \"onClick\": { \"openLink\": { \"url\": \"%s\" } } } }\n" +
	                "            ]}\n" +
	                "          ]\n" +
	                "        }\n" +
	                "      ]\n" +
	                "    }\n" +
	                "  ]\n" +
	                "}",
					start, end, duration, passed, failed, skipped, reportUrl);

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonPayload.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int responseCode = conn.getResponseCode();
			System.out.println("GChat response: " + responseCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
