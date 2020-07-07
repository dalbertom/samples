averages = dict()

# Sort by value
sorted_averages = { k: v for k, v in sorted(averages.items(), key=lambda x: x[1], reverse=False) }
