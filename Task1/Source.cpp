#include <iostream>

using namespace std;

bool ifEven(int& val) {
	bool local = val & 1;
	return local;
}

int main() {

	int x = 0;
	cout << "Enter weight watermelon: "; cin >> x;
	if (ifEven(x))
		cout << "NO" << endl;
	else
		cout << "YES" << endl;

	system("pause");
	return 0;
}