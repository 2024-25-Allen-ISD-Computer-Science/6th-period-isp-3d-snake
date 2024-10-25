using System;
namespace ThreeDSnake.Testing.RuntimeTest
{
	public class RuntimeTest
	{
		public RuntimeTest(string input)
		{
			if (input == null || input == "") {
				System.Diagnostics.Debug.WriteLine("The code is running, you better catch it!");
			} else {
                System.Diagnostics.Debug.WriteLine(input);
            }
		}
	}
}
