assert Integer.MAX_VALUE == 2147483647
assert Integer.MIN_VALUE == -2147483648

assert 110.class == Integer
assert 3000000000.class == Long
assert 10000000000000000000000.class == BigInteger

assert 42i.class == Integer
assert 123L.class == Long
assert 456g.class == BigInteger
assert 0xFFi.class == Integer

assert Integer.SIZE == 32
assert Long.SIZE == 64
assert Short.SIZE == 16
assert Byte.SIZE == 8

assert Integer.TYPE == int
assert Long.TYPE == long
assert Short.TYPE == short
assert Byte.TYPE == byte

assert 45L as Integer == 45i
assert 45L as int == 45i
assert 45L.toInteger() == 45i
assert 23L.intValue() == 23i
assert '42'.toInteger() == 42i

assert Integer.toString(29, 16) == '1d'
assert Integer.valueOf('12af', 16) == 0x12af

assert Integer.decode('0xff') == 0xFF
assert Integer.decode('#FF') == 0xFF

assert Integer.signum(45i) == 1
assert Integer.signum(0i) == 0
assert Integer.signum(-43i) == -1

assert 34 + 33 == 67 && 34.plus(33) == 67
assert 34L - 21L == 13L && 34L.minus(21L) == 13L
assert 3 * 31 == 93 && 3.multiply(31) == 93
assert 23 % 3 == 2 && 23.mod(3) == 2
assert 3 ** 2 == 9 && 3.power(2) == 9

assert (4 <=> 7) == -1 && 4.compareTo(7) == -1

assert Integer.highestOneBit(45) == 32
assert Integer.lowestOneBit(45i) == 1

assert 0xB4F << 4 == 0xB4F0 && 0xB4F.leftShift(4) == 0xB4F0
assert 0xD23C >> 4 == 0xD23 && 0xD23C.rightShift(4) == 0xD23
assert -0xFFF >> 4 == -0x100 && (-0xFFF).rightShift(4) == -0x100
assert -0xFFF >>> 4 == 0xFFFFF00 && (-0xFFF).rightShiftUnsigned(4) == 0xFFFFF00
