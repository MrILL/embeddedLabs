import matplotlib.pyplot as plt
import calc
import signal

HARMONICS = 10
CUTOFF_FREQUENCY = 1200
N = 64

sig = signal.generate(HARMONICS, CUTOFF_FREQUENCY, N)

fig, ax = plt.subplots(2, 1)
fig.tight_layout(rect=(0.03, 0, 1, 0.98))

(fft_vs_dft, x_labels, fft_t, dft_t) = signal.fft_vs_dft(HARMONICS, CUTOFF_FREQUENCY, N)
ax[0].plot(fft_vs_dft)
ax[0].set_title('FFT time / DFT time')

ax[1].plot(x_labels, fft_t, label="fft time")
ax[1].plot(x_labels, dft_t, label="dft time")
ax[1].legend()
ax[1].set_title('Time Comparison')

fig.savefig('fft_vs_dft.png')
plt.show()
