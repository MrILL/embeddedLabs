package com.example.mrills
import kotlin.math.abs
import kotlin.random.Random

class GeneMachine(a: Int, b: Int, c: Int, d: Int, y: Int) {
    private val ca = a
    private val cb = b
    private val cc = c
    private val cd = d
    private val result = y
    private val startPopulation = MutableList<MutableList<Int>>(4){ mutableListOf() }
    private var population: MutableList<MutableList<Int>> = mutableListOf()
    private var fitness = Array(4){0}
    private val childPopulation = MutableList<MutableList<Int>>(4){ mutableListOf() }

    fun solve(): Array<Int> {
        startPopulationInit()
        lifecycle()
        while (!fitness.contains(0) && population.toTypedArray() contentEquals childPopulation.toTypedArray()
        ) {
            startPopulationInit()
            lifecycle()
        }
        fitness.forEachIndexed{i, v ->
            if (v == 0) return population[i].toTypedArray()
        }
        return arrayOf()
    }

    private fun startPopulationInit() {
        for (i in 0 until 4) {
            startPopulation[i] = MutableList(4) {Random.nextInt(1, 8)}
        }
    }

    private fun lifecycle() {
        var iter = 0
        this.calcFitness()
        while (!this.fitness.contains(0) && iter < 10) {
            this.calcFitness()
            this.runRoll()
            this.crossover()
            iter++
        }
    }

    private fun calcFitness() {
        if (this.population.isEmpty()) {
            this.population = this.startPopulation
        }
        for (i in 0 until 4) {
            this.fitness[i] = this.fitnessOf(this.population[i])
        }
    }

    private fun fitnessOf(pop: MutableList<Int>): Int {
        val total: Int = pop[0] * this.ca + pop[1] * this.cb + pop[2] * this.cc + pop[3] * this.cd
        return abs(this.result - total)
    }

    private fun runRoll() {
        var rulet = 0.00
        this.fitness.forEach { rulet += 1 / it }
        val rollChance = Array(4) { i -> 1 / fitness[i] / rulet}
        val rollCircle = Array(4) {0.0}
        rollCircle[0] = rollChance[0]
        for (i in 1 until 4) {
            rollCircle[i] = rollCircle[i - 1] + rollChance[i]
        }
        for (i in 0 until 4) {
            var childIndex = 0
            for (k in 0 until 4) {
                if (Random.nextDouble(1.0) >= rollCircle[k]) {
                    childIndex = k
                }
            }
            childPopulation[i] = population[childIndex]
        }
    }

    private fun crossover() {
        for (p in 0 until 4) {
            population[p] = MutableList(4) { i ->
                val pIndex = if(i<2) p else if(p%2==0) p+1 else p-1
                childPopulation[pIndex][i]
            }
        }
    }
}
