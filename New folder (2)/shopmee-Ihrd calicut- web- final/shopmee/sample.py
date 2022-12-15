from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
analyser = SentimentIntensityAnalyzer()
sent="very bad product"

score = analyser.polarity_scores(sent)

print(score['neu'],score['pos'],score['neg'])

if score['neg'] > score['pos'] and score['neg'] > score['neu']:
    print("its negative")
elif score['pos'] > score['neg'] and  score['pos'] > score['neu']:
    print("its positive")
else:
    print("its neutral")