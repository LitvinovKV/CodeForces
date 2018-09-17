#include <iostream>
#include <string>
#include <math.h>

using namespace std;

string getMinMumber(int numberLength) {
    string result = "";
    if ((numberLength % 2) != 0)
        numberLength++;
    for (int i = 0; i < (numberLength / 2); i++)
            result += '4';
    for (int i = 0; i < (numberLength / 2); i++)
            result += '7';
    return result;
}

bool checkPos(string str) {
    int count4 = 0, count7 = 0;
    for (int i = 0; i < str.length(); i++) {
        if (str[i] == '4') count4++;
        else count7++;
    }
    if (count4 == count7) return true;
    else return false;
}


string calculateNextNumber(string number) {
    for (int i = number.length() - 1; i >= 0; i--) {
        if (number[i] >= '7' && i == 0) {
            number[i] = '4';
            number = '4' + number + '4';
        }
        else if (number[i] >= '7')
            number[i] = '4';    
        else if (number[i] < '7'){
            number[i] = '7';
            break;
        }
    }

    return number;
}

int main() {
    string str;
    cin >> str;
    
    long number = stol(str.c_str());
    if (number < 1 && number > pow(10, 9)) return 0;
    
    string min = getMinMumber(str.length());
    

    if ((stol(min.c_str()) - number) == 0)
        cout << min << endl;
    else if (stol(min.c_str()) < number) {
        while ( (stol(min.c_str()) < number) || (checkPos(min) != true) )
            min = calculateNextNumber(min);
    }
    

    cout << min << endl;

    return 0;
}