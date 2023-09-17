public class StringGenerator {
	public String getString(int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int asciiCode;
			do {
				asciiCode = (int) (127*Math.random());
			} while ((asciiCode < 33) || (asciiCode == 58));
			sb.append((char) asciiCode);
		}
		return sb.toString();
	}	
}