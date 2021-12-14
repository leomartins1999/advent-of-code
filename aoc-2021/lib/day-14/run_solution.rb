require_relative 'solution'

# frozen_string_literal: true

INPUT = '
VFHKKOKKCPBONFHNPHPN

VS -> B
HK -> B
FO -> P
NC -> F
VN -> C
BS -> O
HS -> K
NS -> C
CV -> P
NV -> C
PH -> H
PB -> B
PK -> K
HF -> P
FV -> C
NN -> H
VO -> K
VP -> P
BC -> B
KK -> S
OK -> C
PN -> H
SB -> V
KO -> P
KH -> C
KS -> S
FP -> B
PV -> B
BO -> C
OS -> H
NB -> S
SP -> C
HN -> N
FN -> B
PO -> O
FS -> O
NH -> B
SO -> P
OB -> S
KC -> C
OO -> H
BB -> V
SC -> F
NP -> P
SH -> C
BH -> O
BP -> F
CC -> S
BN -> H
SS -> P
BF -> B
VK -> P
OV -> H
FC -> S
VB -> S
PF -> N
HH -> O
HC -> V
CH -> B
HP -> H
FF -> H
VF -> V
CS -> F
KP -> F
OP -> H
KF -> F
PP -> V
OC -> C
PS -> F
ON -> H
BK -> B
HV -> S
CO -> K
FH -> C
FB -> F
OF -> V
SN -> S
PC -> K
NF -> F
NK -> P
NO -> P
CP -> P
CK -> S
HB -> H
BV -> C
SF -> K
HO -> H
OH -> B
KV -> S
KN -> F
SK -> K
VH -> S
CN -> S
VC -> P
CB -> H
SV -> S
VV -> P
CF -> F
FK -> F
KB -> V
'.freeze

solution = Day14.new

puts 'Solution for Day 14:'
puts format('---> Part 1: %s', solution.solve_first(INPUT))
puts format('---> Part 2: %s', solution.solve_second(INPUT))
