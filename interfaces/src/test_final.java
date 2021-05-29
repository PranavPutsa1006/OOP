interface in1
{
	int a=10;
	void display();
}
class test_final implements in1 
{
	public void display()
	{
		System.out.println("Geek");
	}
	public static void main(String[] args) 
	{
		test_final t= new test_final();
		t.display();
		System.out.println(a);
	}

}
