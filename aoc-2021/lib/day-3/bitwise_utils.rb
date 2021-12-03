EQUAL = -1.freeze

# builds a binary mask with size and left most bit
# with 1 value (all remaining with 0)
def single_bit_mask(size)
  value = '1' << '0' * (size - 1)
  
  value.to_i(2)
end

# builds a binary mask with size and all bits
# with value 1
def full_bit_mask(size)
  value = '1' * size

  value.to_i(2)
end

# for the given mask, determines what is
# the most common bit value
def calculate_column_value(values, mask)
  zeros = 0
  ones = 0
  threshold = values.size / 2 + 1

  values.each do |v|
    v.to_i(2) & mask > 0 ? ones += 1 : zeros += 1

    break if zeros > threshold || ones > threshold
  end

  if zeros == ones
    EQUAL
  elsif zeros > ones
    0
  else
    1
  end
end

def number_of_bits(values)
  values[0].size
end
