#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* multiplyStrings(char* num1, char* num2) {
    if (strcmp(num1, "0") == 0 || strcmp(num2, "0") == 0) {
        char* zeroResult = calloc(2, sizeof(char));
        zeroResult[0] = '0';
        return zeroResult;
    }

    int len1 = strlen(num1);
    int len2 = strlen(num2);
    int productLen = len1 + len2;
    int* product = calloc(productLen, sizeof(int));

    for (int i = len1 - 1; i >= 0; i--) {
        int carry = 0;
        int val1 = num1[i] - '0';

        for (int j = len2 - 1; j >= 0; j--) {
            int val2 = num2[j] - '0';
            int sum = val1 * val2 + product[i + j + 1] + carry;
            product[i + j + 1] = sum % 10;
            carry = sum / 10;
        }

        product[i] += carry;
    }

    int resultLen = productLen;
    while (resultLen > 0 && product[productLen - resultLen] == 0)
        resultLen--;

    char* result = calloc(resultLen + 1, sizeof(char));
    for (int i = 0; i < resultLen; i++)
        result[i] = product[productLen - resultLen + i] + '0';

    free(product);
    return result;
}

int main() {
    char num1[201];
    char num2[201];

    scanf("%s %s", num1, num2);

    char* product = multiplyStrings(num1, num2);

    printf("%s\n", product);

    free(product);

    return 0;
}
