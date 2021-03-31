import random
import math
import time
import calc

def generate(harmonics, cutoff_frequency, n):
    signal = [0] * n
    dW = cutoff_frequency / harmonics
    W = dW
    for i in range(harmonics):
        A = random.random()
        FI = random.random()
        for t in range(n):
            signal[t] += A * math.sin(W * t + FI)
        W += dW
    return signal

def complexity(harmonics, cutoff_frequency, n, step = 10):
    repeat = int(math.log(n, step))
    t = [0] * repeat
    N = [0] * repeat
    curN = step
    for i in range(repeat):
        time_start = time.time()
        generate(harmonics, cutoff_frequency, curN)
        t[i] = time.time() - time_start
        N[i] = curN
        curN = curN * step
    return N, t

def auto_correlation(arr):
    arr_half_size = int(len(arr) / 2)
    arr_half = arr[0:arr_half_size]

    res_x = [0] * arr_half_size
    res_y = [0] * arr_half_size
    for i in range(arr_half_size):
        cur_half = arr[i:(i+arr_half_size)]
        res_x[i] = i*2
        res_y[i] = calc.correlation(arr_half, cur_half)
    return res_x, res_y

def cross_corelation(arr1, arr2):
    arr_half_size = int(len(arr1) / 2)
    arr1_half = arr1[0:arr_half_size]

    res_x = [0] * arr_half_size
    res_y = [0] * arr_half_size
    for i in range(arr_half_size):
        cur_half = arr2[i:(i+arr_half_size)]
        res_x[i] = i*2
        res_y[i] = calc.correlation(arr1_half, cur_half)
    return res_x, res_y

def dft(sig):
    N = len(sig)
    res = [0] * N
    for p in range(N):
        for k in range(N):
            expression = 2 * math.pi / N * p * k
            w = complex(math.cos(expression), -math.sin(expression))
            res[p] += w * sig[k]
        res[p] = abs(res[p])
    return res
    