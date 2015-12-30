package fi.ukkosnetti.voteria.client.ballot;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import fi.ukkosnetti.voteria.common.dto.BallotCreate;

public interface BallotFactory extends AutoBeanFactory {
	AutoBean<BallotCreate> createBallot();
}
