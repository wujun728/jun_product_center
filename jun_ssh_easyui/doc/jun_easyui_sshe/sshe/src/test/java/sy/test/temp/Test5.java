package sy.test.temp;

public class Test5 {

	public static void main(String[] args) {
		String fenceArea = "116.49672_39.92407;116.33364_39.86216";
		String leftTop = fenceArea.split(";")[0];
		String rightBottom = fenceArea.split(";")[1];
		System.out.println(leftTop);
		System.out.println(rightBottom);
		float leftTopLon = Float.parseFloat(leftTop.split("_")[0]);
		float leftTopLat = Float.parseFloat(leftTop.split("_")[1]);
		System.out.println(leftTopLon);
		System.out.println(leftTopLat);
		float rightBottomLon = Float.parseFloat(rightBottom.split("_")[0]);
		float rightBottomLat = Float.parseFloat(rightBottom.split("_")[1]);
		System.out.println(rightBottomLon);
		System.out.println(rightBottomLat);
	}

}
