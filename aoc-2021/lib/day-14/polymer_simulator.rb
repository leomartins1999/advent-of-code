class PolymerSimulator
  def simulate(str, mapper, steps)
    occurrences = map_occurrences(str)

    (1..steps).reduce(occurrences) { |acc, _| get_next(acc, mapper) }
  end

  def get_score(occurrences, last_char)
    tally = {}

    # count appearences
    occurrences.each do |k, v|
      add_or_increment_map_entry(tally, k[0], v)
    end

    # puts last character in tally as where considering
    # the first one in reducer above
    add_or_increment_map_entry(tally, last_char)

    min, max = tally
      .values
      .minmax

    max - min
  end

  private

  def map_occurrences(str)
    res = {}

    (0..str.size - 2).each do |idx|
      key = str[idx] + str[idx + 1]
      add_or_increment_map_entry(res, key)
    end

    res
  end

  def get_next(occurrences, mapper)
    res = {}

    occurrences.each do |seq, cnt|
      start = seq[0]
      _end = seq[1]

      start_seq = start + mapper[seq]
      end_seq = mapper[seq] + _end

      # elements needs to be reinserted by this order
      # otherwise the solution applied in the calculation
      # does not fucking work
      add_or_increment_map_entry(res, start_seq, cnt)
      add_or_increment_map_entry(res, end_seq, cnt)
    end

    res
  end

  def add_or_increment_map_entry(map, key, value = 1)
    map[key] = if map.include?(key)
                 map[key] + value
               else
                 value
               end
  end
end
