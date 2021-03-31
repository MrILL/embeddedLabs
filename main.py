import matplotlib.pyplot as plt
import calc
import signal

HARMONICS = 10
CUTOFF_FREQUENCY = 1200
N = 64

sig = signal.generate(HARMONICS, CUTOFF_FREQUENCY, N)

fig, ax = plt.subplots(2, 1)
fig.tight_layout(rect=(0.03, 0, 1, 0.98))

ax[0].plot(sig)
ax[0].set_title('Signal')

dft = signal.dft(sig)
ax[1].plot(dft)
ax[1].set_title('Spectrum')

fig.savefig('signal_and_spectrum.png')
plt.show()
