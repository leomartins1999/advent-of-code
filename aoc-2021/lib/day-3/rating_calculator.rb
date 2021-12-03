require_relative 'bitwise_utils'

class RatingCalculator

  def calculate_gamma_rate(values, size)
    res = ''

    mask = single_bit_mask(size)
    while mask > 0
      value = calculate_column_value(values, mask)
      res << value.to_s

      mask = mask >> 1
    end

    res.to_i(2)
  end

  def calculate_epsilon_rate(gamma, size)
    mask = full_bit_mask(size)
    gamma ^ mask
  end

  def calculate_oxygen_rating(values, size)
    filter_values(values, size, true, 1)
  end

  def calculate_co2_rating(values, size)
    filter_values(values, size, false, 0)
  end

  private

  def filter_values(values, size, most_common, default_value)
    elems = Array.new(values)
    
    idx = 0
    mask = single_bit_mask(size)
    while elems.size > 1
      value = calculate_column_value(elems, mask)

      elems = filter_non_compliant_values(elems, idx, value, most_common, default_value)

      idx += 1
      mask = mask >> 1
    end

    elems
      .first
      .to_i(2)
  end

  def filter_non_compliant_values(elems, idx, value, most_common, default_value)
    elems.filter do |elem|
      if value == EQUAL
        elem[idx].to_i == default_value
      elsif most_common
        elem[idx].to_i == value
      else
        elem[idx].to_i != value
      end
    end
  end
end
