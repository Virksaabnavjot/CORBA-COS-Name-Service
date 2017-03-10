import Test.*;

class HelloServant extends _HelloImplBase
{
    //public String addNumbers(int num1, int num2, int num3, int num4)
    public String addNumbers()
    {
      //int sum = num1+num2+num3+num4;
      int sum = 4;
		System.out.println("Received a call. "+sum);
		return ""+sum;
    }
}
