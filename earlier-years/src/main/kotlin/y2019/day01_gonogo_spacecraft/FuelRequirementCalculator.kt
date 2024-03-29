package y2019.day01_gonogo_spacecraft

import kotlin.math.max


fun fuelRequired(mass: Int): Int {
    return mass / 3 - 2
}

tailrec fun realFuelRequired(mass: Int, totalMassOfFuel: Int = 0) : Int {
    val fuelRequired = max(fuelRequired(mass), 0)
    if (fuelRequired == 0) {
        return totalMassOfFuel
    }
    return realFuelRequired(fuelRequired, fuelRequired + totalMassOfFuel)
}

fun main() {
    val requiredAmountOfFuel = INPUT.lines()
            .map { it.toInt() }
            .map { fuelRequired(it) }
            .sum()

    println(requiredAmountOfFuel)

    val realAmountOfRequiredFuel = INPUT.lines()
            .map { it.toInt() }
            .map { realFuelRequired(it) }
            .sum()

    println(realAmountOfRequiredFuel)
}


private const val INPUT = """54755
96495
111504
53923
118158
118082
137413
135315
87248
127646
79201
52399
77966
129568
63880
128973
55491
111226
126447
87017
112469
83975
51280
60239
120524
57122
136517
117378
93629
55125
68990
70336
115119
68264
148122
70075
106770
54976
123852
61813
113373
53924
59660
67111
52825
81568
110842
134870
135529
78689
129451
96041
91627
70863
100098
121908
96623
143752
149936
116283
149488
126158
106499
124927
109574
70711
139078
67212
124251
123803
73569
145668
96045
59748
123238
68005
121412
97236
104800
86786
141680
123807
82310
76593
146092
82637
92339
93821
56247
58328
90159
105700
57317
69011
125544
102372
63797
92127
111207
77596"""