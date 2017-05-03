package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import model.DoubleColorBall;
import utils.ReadDoubleColorBall;

public class SelectDoubleColorBallService {
	/** number of balls */
	private final int NUMBER_OF_BALLS = 4;

	private static final String NUMBER_BALLS_CHOSEN_ERROR = "Please choose the right number balls.";

	private static final String NO_RESULT = "Congratulation, you still have chance to win the top prize!!!";

	public static final String SEPARATOR = "|";

	private Collection<DoubleColorBall> allRecords;

	private String first;
	private String second;
	private String third;
	private String fourth;
	private String fifth;
	private String sixth;
	private String blue;

	private void init(final String[] chosenBall) {
		this.first = chosenBall[0];
		this.second = chosenBall[1];
		this.third = chosenBall[2];
		this.fourth = chosenBall[3];
		this.fifth = chosenBall[4];
		this.sixth = chosenBall[5];
		this.blue = chosenBall[6];
		this.allRecords = ReadDoubleColorBall.getInstance().getAllRecords().parallelStream()
				.collect(Collectors.toList());
	}

	public String selectRecord(final String[] chosenBall) {
		if (!checkBallsNumber(chosenBall)) {
			// The number of balls is wrong
			return NUMBER_BALLS_CHOSEN_ERROR;
		}
		init(chosenBall);

		Collection<DoubleColorBall> resultBonus = new ArrayList<>();
		boolean hasFifth = StringUtils.isNotEmpty(this.fifth);
		boolean hasSixth = hasFifth && StringUtils.isNotEmpty(this.sixth);
		boolean hasBlue = StringUtils.isNotEmpty(this.blue);

		if (hasBlue) {
			this.allRecords = this.allRecords.parallelStream().filter(dcb -> dcb.getBlueBall().equals(blue))
					.collect(Collectors.toList());
		}
		if (hasSixth) {
			// 6 red balls
			resultBonus = filter1to6();
		} else if (hasFifth) {
			// 5 red balls
			// Case: 2, 3, 4, 5, 6 position
			case2to6(resultBonus);

			// Case: 1, 3, 4, 5, 6 position
			case13456(resultBonus);

			// Case: 1, 2, 4, 5, 6 position
			case12456(resultBonus);

			// Case: 1, 2, 3, 5, 6position
			case12356(resultBonus);

			// Case: 1, 2, 3, 4, X position
			case1234X(resultBonus);
		} else {
			// 4 red balls
			// Case: 1, 2, 3, 4 position
			resultBonus.addAll(filter1to4());

			// Case: 1, 2, 3, 5 position
			resultBonus.addAll(filter1235());

			// Case: 1, 2, 4, 5 position
			resultBonus.addAll(filter1245());

			// Case: 1, 3, 4, 5 position
			resultBonus.addAll(filter1345());

			// Case: 2, 3, 4, 5 position
			resultBonus.addAll(filter2to5());

			// Case: 1, 2, X, 6 position
			case12X6(resultBonus);

			// Case: X, 3, 4, 6 position
			caseX346(resultBonus);

			// Case: X, 3, 5, 6 position
			caseX356(resultBonus);

			// Case: X, 4, 5, 6 position
			caseX456(resultBonus);

		}

		return processBonus(resultBonus);
	}

	/**
	 * Filter red balls from 1 to 6
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter1to6() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getFirstBall().equals(first))
				.filter(dcb -> dcb.getSecondBall().equals(second)).filter(dcb -> dcb.getThirdBall().equals(third))
				.filter(dcb -> dcb.getFourthBall().equals(fourth)).filter(dcb -> dcb.getFifthBall().equals(fifth))
				.filter(dcb -> dcb.getSixthBall().equals(sixth)).collect(Collectors.toList());
	}

	/**
	 * Filter red balls from 1 to 4
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter1to4() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getFirstBall().equals(first))
				.filter(dcb -> dcb.getSecondBall().equals(second)).filter(dcb -> dcb.getThirdBall().equals(third))
				.filter(dcb -> dcb.getFourthBall().equals(fourth)).collect(Collectors.toList());
	}

	/**
	 * Filter red balls 1, 2, 3, 5
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter1235() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getFirstBall().equals(first))
				.filter(dcb -> dcb.getSecondBall().equals(second)).filter(dcb -> dcb.getThirdBall().equals(third))
				.filter(dcb -> dcb.getFifthBall().equals(fourth)).collect(Collectors.toList());
	}

	/**
	 * Filter red balls 1, 2, 4, 5
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter1245() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getFirstBall().equals(first))
				.filter(dcb -> dcb.getSecondBall().equals(second)).filter(dcb -> dcb.getFourthBall().equals(third))
				.filter(dcb -> dcb.getFifthBall().equals(fourth)).collect(Collectors.toList());
	}

	/**
	 * Filter red balls 1, 3, 4, 5
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter1345() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getFirstBall().equals(first))
				.filter(dcb -> dcb.getThirdBall().equals(second)).filter(dcb -> dcb.getFourthBall().equals(third))
				.filter(dcb -> dcb.getFifthBall().equals(fourth)).collect(Collectors.toList());
	}

	/**
	 * Filter red balls 2, 3, 4, 5
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter2to5() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getSecondBall().equals(first))
				.filter(dcb -> dcb.getThirdBall().equals(second)).filter(dcb -> dcb.getFourthBall().equals(third))
				.filter(dcb -> dcb.getFifthBall().equals(fourth)).collect(Collectors.toList());
	}

	/**
	 * Filter red balls 1, 2, 6
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter126() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getFirstBall().equals(first))
				.filter(dcb -> dcb.getSecondBall().equals(second)).filter(dcb -> dcb.getSixthBall().equals(fourth))
				.collect(Collectors.toList());
	}

	/**
	 * Filter red balls 3, 4, 6
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter346() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getThirdBall().equals(second))
				.filter(dcb -> dcb.getFourthBall().equals(third)).filter(dcb -> dcb.getSixthBall().equals(fourth))
				.collect(Collectors.toList());
	}

	/**
	 * Filter red balls 3, 5, 6
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter356() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getThirdBall().equals(second))
				.filter(dcb -> dcb.getFifthBall().equals(third)).filter(dcb -> dcb.getSixthBall().equals(fourth))
				.collect(Collectors.toList());
	}

	/**
	 * Filter red balls 4, 5, 6
	 * 
	 * @return
	 */
	private Collection<DoubleColorBall> filter4to6() {
		return this.allRecords.parallelStream().filter(dcb -> dcb.getFourthBall().equals(second))
				.filter(dcb -> dcb.getFifthBall().equals(third)).filter(dcb -> dcb.getSixthBall().equals(fourth))
				.collect(Collectors.toList());
	}

	/**
	 * Add filter of red ball on position 5 and 6
	 * 
	 * @param hasFiltered
	 * @param bonus
	 */
	private void add5And6Position(final Collection<DoubleColorBall> hasFiltered, Collection<DoubleColorBall> bonus) {
		// add 5 filter
		bonus.addAll(hasFiltered.parallelStream().filter(dcb -> dcb.getFifthBall().equals(fifth))
				.collect(Collectors.toList()));
		add6Position(hasFiltered, bonus);

	}

	/**
	 * Add filter of red ball on position 6
	 * 
	 * @param hasFiltered
	 * @param bonus
	 */
	private void add6Position(final Collection<DoubleColorBall> hasFiltered, Collection<DoubleColorBall> bonus) {
		// add 6 filter
		bonus.addAll(hasFiltered.parallelStream().filter(dcb -> dcb.getSixthBall().equals(fifth))
				.collect(Collectors.toList()));
	}

	/**
	 * When numbers are on position of red ball 1, 2, 3, 4, X <br/>
	 * X means variable on other position
	 * 
	 * @param bonus
	 */
	private void case1234X(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> fourFilter = filter1to4();
		add5And6Position(fourFilter, bonus);
	}

	/**
	 * When numbers are on position of red ball 1, 2, 3, 5, 6 <br/>
	 * 
	 * @param bonus
	 */
	private void case12356(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> fourFilter = filter1235();
		add6Position(fourFilter, bonus);
	}

	/**
	 * When numbers are on position of red ball 1, 2, 4, 5, 6 <br/>
	 * 
	 * @param bonus
	 */
	private void case12456(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> fourFilter = filter1245();
		add6Position(fourFilter, bonus);
	}

	/**
	 * When numbers are on position of red ball 1, 3, 4, 5, 6 <br/>
	 * 
	 * @param bonus
	 */
	private void case13456(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> fourFilter = filter1345();
		add6Position(fourFilter, bonus);
	}

	/**
	 * When numbers are on position of red ball 2, 3, 4, 5, 6 <br/>
	 * 
	 * @param bonus
	 */
	private void case2to6(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> fourFilter = filter2to5();
		add6Position(fourFilter, bonus);
	}

	/**
	 * When numbers are on position of red ball 1, 2, X, 6 <br/>
	 * X means variable on other position
	 * 
	 * @param bonus
	 */
	private void case12X6(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> threeFilter = filter126();
		// 1, 2, 3, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getThirdBall().equals(this.third))
				.collect(Collectors.toList()));
		// 1, 2, 4, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getFourthBall().equals(this.third))
				.collect(Collectors.toList()));
		// 1, 2, 5, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getFifthBall().equals(this.third))
				.collect(Collectors.toList()));
	}

	/**
	 * When numbers are on position of red ball X, 3, 4, 6 <br/>
	 * X means variable on other position
	 * 
	 * @param bonus
	 */
	private void caseX346(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> threeFilter = filter346();
		// 1, 3, 4, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getFirstBall().equals(this.first))
				.collect(Collectors.toList()));
		// 2, 3, 4, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getSecondBall().equals(this.first))
				.collect(Collectors.toList()));
	}

	/**
	 * When numbers are on position of red ball X, 3, 5, 6 <br/>
	 * X means variable on other position
	 * 
	 * @param bonus
	 */
	private void caseX356(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> threeFilter = filter356();
		// 1, 3, 5, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getFirstBall().equals(this.first))
				.collect(Collectors.toList()));
		// 2, 3, 5, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getSecondBall().equals(this.first))
				.collect(Collectors.toList()));
	}

	/**
	 * When numbers are on position of red ball X, 4, 5, 6 <br/>
	 * X means variable on other position
	 * 
	 * @param bonus
	 */
	private void caseX456(Collection<DoubleColorBall> bonus) {
		final Collection<DoubleColorBall> threeFilter = filter4to6();
		// 1, 4, 5, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getFirstBall().equals(this.first))
				.collect(Collectors.toList()));
		// 2, 4, 5, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getSecondBall().equals(this.first))
				.collect(Collectors.toList()));
		// 3, 4, 5, 6
		bonus.addAll(threeFilter.parallelStream().filter(dcb -> dcb.getSecondBall().equals(this.first))
				.collect(Collectors.toList()));
	}

	/**
	 * Check the number of balls.<br>
	 * If there is not enough balls, return false.
	 * 
	 * @param chosenBall
	 * @return
	 */
	private boolean checkBallsNumber(final String[] chosenBall) {
		for (int i = 0; i < NUMBER_OF_BALLS; i++) {
			if (StringUtils.isEmpty(chosenBall[i])) {
				// The number of balls is wrong
				return false;
			}
		}
		return true;
	}

	/**
	 * Convert result into string message
	 * 
	 * @param bonus
	 * @return
	 */
	private String processBonus(Collection<DoubleColorBall> bonus) {
		StringBuilder sb = new StringBuilder();
		if (CollectionUtils.isEmpty(bonus)) {
			sb.append(NO_RESULT);
		} else {
			for (DoubleColorBall dcb : bonus) {
				// sb.append(dcb.getIssueNumber());
				// sb.append(",");
				sb.append(dcb.getFirstBall());
				sb.append(",");
				sb.append(dcb.getSecondBall());
				sb.append(",");
				sb.append(dcb.getThirdBall());
				sb.append(",");
				sb.append(dcb.getFourthBall());
				sb.append(",");
				sb.append(dcb.getFifthBall());
				sb.append(",");
				sb.append(dcb.getSixthBall());
				sb.append(",");
				sb.append(dcb.getBlueBall());
				sb.append(SEPARATOR);
			}
		}
		return sb.toString();
	}

	/**
	 * For test: Generate 4 position number of 6
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private String generateItem(int a, int b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 7; i++) {
			if (a == i || b == i) {
				continue;
			}
			sb.append(i);
			sb.append(",");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		for (int i = 1; i < 7; i++) {
			for (int j = i + 1; j <= 6; j++) {
				String item = new SelectDoubleColorBallService().generateItem(i, j);
				System.out.println("4 balls: " + item);
			}
			String item = new SelectDoubleColorBallService().generateItem(i, 0);
			System.out.println("5 balls: " + item);
		}
	}
}
