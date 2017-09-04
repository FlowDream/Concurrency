/**
 * 
 */
package chapter06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 在预定时间内请求旅游报价
 */
public class RankedTravelQuote {
	
	private final ExecutorService exec = Executors.newFixedThreadPool(10);

	public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies,
			Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException {

		List<QuoteTask> tasks = new ArrayList<>();

		for (TravelCompany company : companies) {
			tasks.add(new QuoteTask(company, travelInfo));
		}

		List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);

		List<TravelQuote> quotes = new ArrayList<>(tasks.size());

		Iterator<QuoteTask> taskIter = tasks.iterator();

		for (Future<TravelQuote> f : futures) {
			@SuppressWarnings("unused")
			QuoteTask task = taskIter.next();
			try {
				quotes.add(f.get());
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		Collections.sort(quotes, ranking);

		return quotes;
	}

	private static class QuoteTask implements Callable<TravelQuote> {
		private final TravelCompany company;
		private final TravelInfo travelInfo;

		public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
			this.company = company;
			this.travelInfo = travelInfo;
		}

		@Override
		public TravelQuote call() throws Exception {
			return company.solicitQuote(travelInfo);
		}

	}

	private static class TravelQuote {
	}

	private static class TravelCompany {

		public TravelQuote solicitQuote(TravelInfo travelInfo) {
			return null;
		}

	}

	private static class TravelInfo {

	}
}
