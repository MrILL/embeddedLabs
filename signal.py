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

def fft(sig):
    n = len(sig)
    if n == 1:
        return sig

    half_size = int(n / 2)
    p_even = [0] * half_size
    p_odd = [0] * half_size
    for i in range(half_size):
        p_even[i] = sig[i*2]
        p_odd[i] = sig[i*2 + 1]

    y_even = fft(p_even)
    y_odd = fft(p_odd)

    res = [0] * n
    for i in range(half_size):
        expression = 2.0 * math.pi * i / n
        w = complex(math.cos(expression), math.sin(expression))
        res[i] = y_even[i] + y_odd[i] * w
        res[i + half_size] = y_even[i] - y_odd[i] * w

    return res

def real(sig):
    for i in range(len(sig)):
        sig[i] = abs(sig[i])
    return sig

def fft_vs_dft(harmonics, cutoff_frequency, n):
    N_start = 2**6
    N = N_start
    lenght = int(math.log2(2**(13+1)/N_start))
    res_y = [0] * lenght
    res_x = [0] * lenght
    fft_t = [0] * lenght
    dft_t = [0] * lenght
    for i in range(lenght):
        sig = generate(harmonics, cutoff_frequency, N)

        time_start = time.time()
        fft(sig)
        fft_time = time.time() - time_start

        time_start = time.time()
        dft(sig)
        dft_time = time.time() - time_start

        res_y[i] = fft_time / dft_time
        res_x[i] = '2^' + str(5 + int(math.log2(N/N_start)))
        fft_t[i] = fft_time
        dft_t[i] = dft_time

        N = N * 2
    return (res_y, res_x, fft_t, dft_t)
