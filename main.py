import matplotlib.pyplot as plt
import calc
import signal

HARMONICS = 10
CUTOFF_FREQUENCY = 1200
N = 64

sig = signal.generate(HARMONICS, CUTOFF_FREQUENCY, N)
print("expected value:", calc.expected_value(sig))
print("dispersion:", calc.dispersion(sig))

fig, ax = plt.subplots(2, 1)
fig.tight_layout(rect=(0.03, 0, 1, 0.98))

ax[0].plot(sig)
ax[0].set_title('Signal')

(complexity_n, complexity_time) = signal.complexity(HARMONICS, CUTOFF_FREQUENCY, 1000000, 10)
ax[1].plot(complexity_n, complexity_time)
ax[1].set_title('Complexity')

fig.savefig('signal_and_algorithm_complexity.png')
plt.show()
