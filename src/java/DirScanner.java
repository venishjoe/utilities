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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import java.io.*;
import java.util.*;
import java.nio.file.attribute.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.LinkOption;
import java.nio.file.Files;

public class DirScanner {
	public static void main (String args[]) {
		System.out.println("Inside Main...");
		startScan();		
	}
	
	public static void startScan() {
		try {
			System.out.println("Inside startScan...");
			FileUtils f = new FileUtils();
			String[] extn = {"txt","pdf"};
			Iterator it = FileUtils.iterateFiles(new File("D:/Libraries/"), extn, true);
			//Iterator it = FileUtils.iterateFilesAndDirs(new File("D:/Libraries/"),FileFilterUtils.falseFileFilter() ,FileFilterUtils.trueFileFilter());
			while(it.hasNext()){
				File fileloop = (File) it.next();
				
				System.out.println(fileloop.getName());
				System.out.println(f.sizeOf(fileloop));
				System.out.println(fileloop.getAbsolutePath());
				
				Path myDir = Paths.get(fileloop.getAbsolutePath());
				BasicFileAttributes attr = Files.readAttributes(myDir, BasicFileAttributes.class);
				
				System.out.println("creationTime: " + attr.creationTime());
				System.out.println("lastAccessTime: " + attr.lastAccessTime());
				System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
				System.out.println("\n\n");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}