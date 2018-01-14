#include <iostream>
#include <string>
#include <cstdio>

using namespace std;

unsigned char countSuccess(int* mass, int& countScore, int& countStudent) {
	unsigned char countSS = 0;
	for (unsigned char i = 0; i < countStudent; i++)
	{
		if (mass[i] >= mass[countScore])
			countSS++;
		else
			return countSS;
	}
}

int main() {
	FILE* f = fopen("TaskFile.txt", "r");
	int* countStudent = new int;
	int* countScore = new int;
	int* mass = new int;
	

	if (f) {
		fscanf(f, "%d", countStudent);
		fscanf(f, "%d", countScore);
		if ((*countStudent > 50) && (*countScore > *countStudent) && (*countScore < 1)) {
			cout << "ERROR FILE VALUES!" << endl;
			return 0;
		}
		mass = new int[*countStudent];

		unsigned char i = 0;
		while (!feof(f)) {
			fscanf(f, "%d", &mass[i]);
			if ((mass[i] > 100) && (mass[i] < 0)) {
				cout << "ERROR FILE VALUES!" << endl;
				return 0;
			}
			i++;
		}
	}
	else {
		cout << "ERROR OPEN FILE!" << endl;
		return 0;
	}

	fclose(f);

	cout << (int)countSuccess(mass, *countScore, *countStudent) << endl;
	
	delete countStudent;
	countStudent = 0;
	delete countScore;
	countScore = 0;
	delete[] mass;
	mass = 0;

	system("pause");
	return 0;
}