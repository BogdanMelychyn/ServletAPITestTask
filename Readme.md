

## Servlet based API for querying text file

###### Query parameters:

**- limit:** integer which represents max number of chars in text. If
parameter is blank or missing return max 10000 chars.

**- q:** string which represents text to search in file, i.e. if it q=java - return all
strings which equals to ‘java’ or containing it. If 'q' is blank or missing - return
all text from file

**- length:** integer which represents max string length. API return string which
doesn’t exceed that number or if there is no such strings empty response. If parameter
is blank or missing ignore it.

**- metaData:** boolean which if set to true will expose file metadata in API response alongside textual content. 
That metadata contain following: fileName, fileSize (in KB), fileCreationDate. If
parameter is blank or missing set it as a ‘false’ value.

###### Example of API call/response:

> `/ServletAPITestTask/readLines?q=Java&length=160&limit=1000&metaData=true`

```
{
  "text": [
    "Java sdlfjsldgjururuhgvnvgaxtiyefvyj",
    "Javac,uhtemorjqonjijdsjdvqqaxsnqfmjn",
    "JavaScript cpiqxlgoigbsavwrkchfcqhaciolvc",
    "fiwumfwqe JavaSE jysohiqyhhuxjjoxxtnsl",
    "vrlhatsdawcmujtkhqpvdjbbghgysq JavaEE "
  ],
  "metaData": {
    "fileName": "test.txt",
    "fileSize": "3KB",
    "fileCreationDate": "2016-01-23T13:02:49.316008Z"
  }
}
```
