#include <iostream>
#include <string>

using namespace std;

bool vowelChar(char& ch) {
	switch (ch)
	{
	case 'a':
	case 'o':
	case 'y':
	case 'e':
	case 'u':
	case 'i':
		return true;
	default:
		return false;
	}
}

void changeString(char**& str, short* lengthStr) {
	for (unsigned int i = 0; i < *lengthStr; i++) {
		if (vowelChar(*str[i])) {//if char is constant, then delete this constant
			delete str[i];
			for (unsigned int j = i; j < *lengthStr - 1; j++)
				str[j] = str[j + 1];
			str[*lengthStr - 1] = 0;
			*lengthStr = *lengthStr - 1;
			i--;
		}
		else { // else if char is n't constant, then add '.' before vowel
			char** newppStr = new char*[*lengthStr + 1];
			for (unsigned int j = 0; j < i; j++)
				newppStr[j] = str[j];
			newppStr[i] = new char('.');
			for (unsigned j = i; j < *lengthStr; j++)
				newppStr[j + 1] = str[j];
			*lengthStr = *lengthStr + 1;
			i++;
			delete[] str;
			str = newppStr;
			newppStr = 0;
		}
	}
}

bool checkString(char** str, short* lengthStr) {
	for (unsigned char i = 0; i < *lengthStr; i++)
	{
		// 'A' - 'Z' (65 - 90) or 'a' - 'z' (97 - 122)
		if (((int)*str[i] >= 65) && ((int)*str[i] <= 90))  //if char is Upper, make lowercase
			*str[i] = (char)((int)*str[i] + 32);
		else if (!(((int)*str[i] >= 97) && ((int)*str[i] <= 122))) {
			cout << "ERROR ENTER CHAR = " << *str[i] << endl;
			return false;
		}
	}
	return true;
}



int main() {
	char* str = new char[100];
	cout << "ENTER WORD: "; scanf("%100s", str);
	short* strLen = new short(strlen(str));

	char** ppStr = new char*[*strLen];
	for (int i = 0; i < *strLen; i++) {
		ppStr[i] = new char;
		*ppStr[i] = str[i];
	}
	delete[] str;
	str = 0;

	if (checkString(ppStr, strLen))
		changeString(ppStr, strLen);

	for (unsigned int i = 0; i < *strLen; i++) {
		cout << *ppStr[i];
	}
	cout << "\n" << endl;

	//Release memory
	for (int i = 0; i < *strLen; i++) {
		delete ppStr[i];
	}
	delete[] ppStr;
	ppStr = 0;
	delete strLen;

	system("pause");
	return 0;
}