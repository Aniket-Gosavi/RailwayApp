package com.aniket.cases;

import com.aniket.model.DbSequence;
import com.aniket.service.SequenceGeneratorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SequenceGeneratorServiceTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private SequenceGeneratorService sequenceGeneratorService;

    @Test
    void testGetSequenceNum() {
        // Arrange
        String seqName = "test-sequence";
        Query query = new Query(Criteria.where("id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true).upsert(true);

        DbSequence counter = new DbSequence();
        counter.setId(seqName);
        counter.setSeq(1);

        when(mongoOperations.findAndModify(query, update, any(FindAndModifyOptions.class), DbSequence.class))
                .thenReturn(counter);

        // Act
        int sequenceNum = sequenceGeneratorService.getSequenceNum(seqName);

        // Assert
        assertEquals(1, sequenceNum);
        verify(mongoOperations, times(1)).findAndModify(query, update, any(FindAndModifyOptions.class), DbSequence.class);
    }
}
