/*******************************************************************************
Copyright (c) 2017 Venish Joe Clarence (http://venishjoe.net)

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

package main

import (
	"flag"
	"fmt"
	"os"
	"path/filepath"
)

func main() {
	//Command line arguments with directory which has files to be renamed and the new file name which has to be appended
	rootDirectory := flag.String("dir", "", "Root Directory Path")
	newFileNameAppend := flag.String("name", "", "New File Name to Append")
	newFileNumberOfCharsToRemove := flag.Int("del", 0, "Number of Characters to be deleted from Prefix")
	flag.Parse()

	//Hardcoded assumptions
	filePathDelimiter := "\\"
	fileNameConnector := " - "

	var listOfAllFiles []string
	counter := 1

	flag.Parse()

	//Get all files in given directory
	errWalk := filepath.Walk(*rootDirectory, func(path string, info os.FileInfo, err error) error {
		listOfAllFiles = append(listOfAllFiles, path)
		return nil
	})
	if errWalk != nil {
		panic(errWalk)
	}
	for _, file := range listOfAllFiles {
		fi, errStat := os.Stat(file)
		if errStat != nil {
			panic(errStat)
		}
		//Process only files
		if !fi.IsDir() {
			formatPadding := fmt.Sprintf("%02d", counter)
			//Rename - Remove first n characters from filename and append new file name along with delimiter followed by original filename
			errRename := os.Rename(file,
				filepath.Dir(file)+filePathDelimiter+*newFileNameAppend+formatPadding+fileNameConnector+filepath.Base(file)[*newFileNumberOfCharsToRemove:])
			counter++
			if errRename != nil {
				fmt.Println(errRename)
				return
			}
		}
	}
}
