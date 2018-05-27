/*******************************************************************************
Copyright (c) 2018 Venish Joe Clarence (http://venishjoe.net)

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
	"bufio"
	"flag"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"strings"
)

func main() {
	/* Utility to call multiple GET/PUT requests. Useful for any bulk operations
	 * using web services.
	 * 
	 * Usage: go run gowebservice.go --filename=/tmp/input.txt
	 * 
	 * Input file format will be GET/PUT```PAYLOAD
	 * Example:
	 *		PUT```https://webservice?queryparams
	 *		GET```https://webservice?queryparams
	 */
	inputFileName := flag.String("filename", "", "Input File Name")
	flag.Parse()

	file, errorOSOpen := os.Open(*inputFileName)
	handleFatalError(errorOSOpen)
	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		methodAndPayload := strings.Split(scanner.Text(), "```")
		if methodAndPayload[0] == "GET" {
			getResponse, getError := http.Get(methodAndPayload[1])
			if getError != nil {
				fmt.Println("HTTP GET request failed with error: ", getError)
			} else {
				defer getResponse.Body.Close()
				getContents, getErrorRead := ioutil.ReadAll(getResponse.Body)
				if getErrorRead != nil {
					log.Fatal(getErrorRead)
				}
				fmt.Println("Response Size: ", len(string(getContents)))
				fmt.Println("Response Code: ", getResponse.StatusCode)
				getResponseHeaders := getResponse.Header
				for key, value := range getResponseHeaders {
					//Uncomment to print headers
					//fmt.Println("   ", key, ":", value)
					_ = key
					_ = value
				}
				//Uncomment to print response
				//fmt.Println(getContents)
			}
		} else if methodAndPayload[0] == "PUT" {
			httpClient := &http.Client{}
			putRequest, putError := http.NewRequest(http.MethodPut, methodAndPayload[1], strings.NewReader(""))
			handleFatalError(putError)
			putResponse, putErrorResponse := httpClient.Do(putRequest)
			if putErrorResponse != nil {
				log.Fatal(putErrorResponse)
			} else {
				defer putResponse.Body.Close()
				putContents, putErrorRead := ioutil.ReadAll(putResponse.Body)
				if putErrorRead != nil {
					log.Fatal(putErrorRead)
				}
				fmt.Println("Response Size: ", len(string(putContents)))
				fmt.Println("Response Code: ", putResponse.StatusCode)
				putResponseHeaders := putResponse.Header
				for key, value := range putResponseHeaders {
					//Uncomment to print headers
					//fmt.Println("   ", key, ":", value)
					_ = key
					_ = value
				}
				//Uncomment to print response
				//fmt.Println(putContents)
			}
		}
	}
}

//Function to handle fatal errors
func handleFatalError(fatalError error) {
	if fatalError != nil {
		fmt.Println(fatalError)
		return
	}
}
