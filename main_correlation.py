import matplotlib.pyplot as plt
import signal

HARMONICS = 10
CUTOFF_FREQUENCY = 1200
N = 64

sig1 = signal.generate(HARMONICS, CUTOFF_FREQUENCY, N)
sig2 = signal.generate(HARMONICS, CUTOFF_FREQUENCY, N)

fig, ax = plt.subplots(2, 1)
fig.tight_layout(rect=(0.03, 0, 1, 0.98))

(x_auto_corr, y_auto_corr) = signal.auto_correlation(sig1)
ax[0].plot(sig1, label="signal")
ax[0].plot(x_auto_corr, y_auto_corr, lw=3, c="#3ab55b", label="auto corelation")
ax[0].set_title('AutoCorrelation')
ax[0].legend()

(x_cross_corr, y_auto_corr) = signal.cross_corelation(sig1, sig2)
ax[1].plot(sig1, label="first signal")
ax[1].plot(sig2, label="second signal")
ax[1].plot(x_cross_corr, y_auto_corr, lw=3, c="#3ab55b", label="cross corelation")
ax[1].set_title('CrossCorrelation')
ax[1].legend()

fig.savefig('cros-_and_autocorrelation.png')
plt.show()
