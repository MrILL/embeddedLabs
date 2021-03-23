def expected_value(arr):
    sum = 0
    for i in range(len(arr)):
        sum += arr[i]
    return sum / len(arr)

def dispersion(arr):
    exp_val = expected_value(arr)
    sum = 0
    for i in range(len(arr)):
        sum += (exp_val - arr[i]) ** 2
    return sum / len(arr)

def correlation(arr1, arr2):
    exp_val1 = expected_value(arr1)
    exp_val2 = expected_value(arr2)
    sum = 0
    for i in range(len(arr1)):
        sum += (arr1[i] - exp_val1) * (arr2[i] - exp_val2)
    return sum / (len(arr1))
    