import Test.*;

class HelloServant extends _HelloImplBase
{
    public int addNumbers(int a, int b, int c, int d)
    {
      int sum = a + b + c + d;
      //int sum = 4;
		System.out.println("Received a call. "+sum);
		return sum;
    }
}
