#include <iostream>
#include <string>

using namespace std;

string BigString(string str) {
	string newStr = "";
	newStr += str[0];
	newStr += to_string(str.length() - 2);
	newStr += str[str.length() - 1];
	return newStr;
}

int main() {

	string str1 = "";
	getline(cin, str1);

	if (str1.length() < 10)
		cout << str1 << endl;
	else
		cout << BigString(str1) << endl;

	system("pause");
	return 0;
}