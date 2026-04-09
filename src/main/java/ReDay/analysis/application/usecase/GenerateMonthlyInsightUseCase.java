package ReDay.analysis.application.usecase;

import ReDay.analysis.domain.service.MonthlyInsightSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerateMonthlyInsightUseCase {

    private final MonthlyInsightSaveService monthlyInsightSaveService;

    public void execute(Long userId, int year, int month) {
        monthlyInsightSaveService.generateAndSave(userId, year, month);
    }
}
