public class PointTest
{
	public static void main(String[] args)
	{
		test1();
	}

	public static void test1()
	{
		String str = "SUNKEN AT x:4,y:4,x:3,y:3,x:2,y:2";
		try {
			Point[] points = PointFactory.pointsFromString(str);
			assert 4 == points[0].getX() ;
			assert 4 == points[0].getY() ;
			assert 3 == points[1].getX() ;
			assert 3 == points[1].getY() ;
			assert 2 == points[2].getX() ;
			assert 2 == points[2].getY() ;
		} 
		catch (PointConversionException e)
		{
			System.out.println(e);
		}
	}
}
