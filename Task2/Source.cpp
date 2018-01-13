#include <iostream>

using namespace std;

int countPlate(int& n, int& m, int& a) {
	int localWidth = 0, localLength = 0, localPlate = 0;
	localWidth = ceil((double)n / (double)a); // ceil - округлить вверх до ближайшего целого
	localLength = ceil((double)m / (double)a); 
	return localPlate = localWidth * localLength;
}

int main() {

	int n = 0, m = 0, a = 0;

	cout << "Enter Width ( must be >= 1) = "; cin >> n;
	if (n < 1) {
		cout << "Invalid input - width!" << endl;
		return 0;
	}

	cout << "Enter length ( must be >= 1) = "; cin >> m;
	if (m < 1) {
		cout << "Invalid input - length!" << endl;
		return 0;
	}

	cout << "Enter size plate (must be <= 10^9) = "; cin >> a;
	if (a > pow(10, 9)) {
		cout << "Invalid input - size plate!" << endl;
		return 0;
	}

	cout << countPlate(n, m, a) << endl;

	system("pause");
	return 0;
}