#include <iostream>
#include <string>
#include <cstring>

using namespace std;

int main() {
    string str;
    cin >> str;
    
    if (str.length() > 100) {
        cout << "NO" << endl;
        return 0;
    }
    
    int count = 0;
    bool flag_1 = false, flag_0 = false;
    char s = 'z';
    for (int i = 0; i < str.length(); i++) {
        if (str[i] == '1'  && flag_1 == false) flag_1 = true;
        if (str[i] == '0' && flag_0 == false) flag_0 = true;
        if (count != 7) {
            if (s != str[i]) {
                s = str[i];
                count = 1;
            }
            else 
                count++;
        }
    }

    if (flag_0 == false || flag_1 == false)
        cout << "NO" << endl;
    else if (count == 7)
        cout << "YES" << endl;
    else
        cout << "NO" << endl;
    return 0;
}