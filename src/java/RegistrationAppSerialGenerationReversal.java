/*******************************************************************************
 Copyright (c) 2010 Venish Joe Clarence (http://venishjoe.net)

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 ******************************************************************************/

public class RegistrationAppSerialGenerationReversal {
/*
Serial Key Format
-----------------
Generate the Hashes for the given String with MD2, MD5 & SHA1 and merge them into
a single String. The length of the string calculated by this method should be 104.
Then the following method is used to calculate the Serial from String.

					  MD2 + MD5 + SHA1 -> 104
					---------------------------
					 4       5      4       5
					XXXX - XXXXX - XXXX - XXXXX
					||||   |||||   ||||   |||||
					||||   |||||   ||||   ||||99
					||||   |||||   ||||   |||15
					||||   |||||   ||||   ||102
					||||   |||||   ||||   |53
					||||   |||||   ||||   27
					||||   |||||   |||85
					||||   |||||   ||18
					||||   |||||   |65
					||||   |||||   47
					||||   ||||98
					||||   |||72
					||||   ||73
					||||   |91
					||||   2
					|||50
					||100
					|76
					32
End of Algorithm
*/
	public static void main (String args[]) throws
		java.io.IOException,
		java.security.NoSuchAlgorithmException {
		RegistrationAppSerialGenerationReversal registrationAppSerialGenerationReversal =
			new RegistrationAppSerialGenerationReversal();
		java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader( System.in );
		java.io.BufferedReader bufferedReader = new java.io.BufferedReader( inputStreamReader );
		System.out.print ( "Enter Full Name: " );
		String fullNameString = bufferedReader.readLine().trim();
		System.out.println("");
		System.out.print ("Enter the Serial Number: ");
		String serialNumber = bufferedReader.readLine().trim();

		String serialNumberEncoded = registrationAppSerialGenerationReversal.calculateSecurityHash(fullNameString,"MD2")
							+ registrationAppSerialGenerationReversal.calculateSecurityHash(fullNameString,"MD5")
							+ registrationAppSerialGenerationReversal.calculateSecurityHash(fullNameString,"SHA1");

		String serialNumberCalc = ""
							+ serialNumberEncoded.charAt(32)
							+ serialNumberEncoded.charAt(76)
							+ serialNumberEncoded.charAt(100)
							+ serialNumberEncoded.charAt(50)
							+ "-"
							+ serialNumberEncoded.charAt(2)
							+ serialNumberEncoded.charAt(91)
							+ serialNumberEncoded.charAt(73)
							+ serialNumberEncoded.charAt(72)
							+ serialNumberEncoded.charAt(98)
							+ "-"
							+ serialNumberEncoded.charAt(47)
							+ serialNumberEncoded.charAt(65)
							+ serialNumberEncoded.charAt(18)
							+ serialNumberEncoded.charAt(85)
							+ "-"
							+ serialNumberEncoded.charAt(27)
							+ serialNumberEncoded.charAt(53)
							+ serialNumberEncoded.charAt(102)
							+ serialNumberEncoded.charAt(15)
							+ serialNumberEncoded.charAt(99);

		if (serialNumber.equals(serialNumberCalc))
			System.out.println("Serial MATCH");
		else
			System.out.println("Serial MIS-MATCH");
	}

	private String calculateSecurityHash(String stringInput, String algorithmName)
		throws java.security.NoSuchAlgorithmException {
		String hexMessageEncode = "";
		byte[] buffer = stringInput.getBytes();
		java.security.MessageDigest messageDigest =
			java.security.MessageDigest.getInstance(algorithmName);
		messageDigest.update(buffer);
		byte[] messageDigestBytes = messageDigest.digest();
		for (int index=0; index < messageDigestBytes.length ; index ++) {
			int countEncode = messageDigestBytes[index] & 0xff;
			if (Integer.toHexString(countEncode).length() == 1) hexMessageEncode = hexMessageEncode + "0";
			hexMessageEncode = hexMessageEncode + Integer.toHexString(countEncode);
		}
		return hexMessageEncode;
	}
}