import random
import math
import time
import matplotlib.pyplot as plt

HARMONICS = 10
CUTOFF_FREQUENCY = 1200
N = 64

def generate_signal(harmonics, cutoff_frequency, n):
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

def calc_complexity(n, step = 10):
    repeat = int(math.log(n, step))
    t = [0] * repeat
    N = [0] * repeat
    curN = step
    for i in range(repeat):
        time_start = time.time()
        generate_signal(HARMONICS, CUTOFF_FREQUENCY, curN)
        t[i] = time.time() - time_start
        N[i] = curN
        curN = curN * step
    return N, t

def calc_expected_value(arr):
    sum = 0
    for i in range(len(arr)):
        sum += arr[i]
    return sum / len(arr)

def calc_dispersion(arr):
    expected_value = calc_expected_value(arr)
    sum = 0
    for i in range(len(arr)):
        sum += (expected_value - arr[i]) ** 2
    return sum / len(arr)

signal = generate_signal(HARMONICS, CUTOFF_FREQUENCY, N)
print("expected value:", calc_expected_value(signal))
print("dispersion:", calc_dispersion(signal))

fig, ax = plt.subplots(2, 1)
fig.tight_layout(rect=(0.03, 0, 1, 0.98))

ax[0].plot(signal)
ax[0].set_title('Signal')

(complexity_n, complexity_time) = calc_complexity(1000000, 10)
ax[1].plot(complexity_n, complexity_time)
ax[1].set_title('Complexity')

fig.savefig('signal_and_algorithm_complexity.png')
plt.show()
